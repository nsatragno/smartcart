import mercury
import time
import urllib2

# Settings
POWER=1500 # centidBm
URL="http://192.168.1.109:3000"
CHANGO_ID=1
TIME_BETWEEN_READS = 3 # seconds

print("clearing existing tags")
try:
    print(urllib2.urlopen(URL + "/changos/" + str(CHANGO_ID) + "/limpiar_tags.json").read())
except urllib2.HTTPError as e:
    print("Error: " + str(e))

print("setting up reader")
reader = mercury.Reader("tmr:///dev/ttyUSB0", baudrate=9600)
reader.set_region("open")
reader.set_read_plan([1], "GEN2", read_power=POWER)
reader.set_read_powers([1], [POWER])

def getepc(tag):
    return tag.epc

tags = {}
print("reading...")
while True:
    new_tags = map(getepc, reader.read())
    for tag in new_tags:
        if not tags.get(tag):
            print("added tag " + tag)
            print("response from server")
            try:
                print(urllib2.urlopen(URL + "/changos/" + str(CHANGO_ID) + "/insertar_tag/" + tag + ".json").read())
                tags[tag] = True
            except urllib2.HTTPError as e:
                print("Error: " + str(e))

    removed_tags = []
    for tag in tags:
        if tag not in new_tags:
            print("removed tag " + str(tag))
            try:
                print(urllib2.urlopen(URL + "/changos/" + str(CHANGO_ID) + "/remover_tag/" + tag + ".json").read())
                removed_tags.append(tag)
            except urllib2.HTTPError as e:
                print("Error: " + str(e))

    for tag in removed_tags:
        del tags[tag]

    time.sleep(TIME_BETWEEN_READS)

print("bye bye!")
