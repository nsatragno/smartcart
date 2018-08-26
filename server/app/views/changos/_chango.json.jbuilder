json.extract! chango, :id, :codigo

json.tags do
  json.array! chango.tags, partial: "tags/tag", as: "tag"
end
json.url chango_url(chango, format: :json)
