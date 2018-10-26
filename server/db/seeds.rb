# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rails db:seed command (or created alongside the database with db:setup).
#
# Examples:
#
#   movies = Movie.create([{ name: 'Star Wars' }, { name: 'Lord of the Rings' }])
#   Character.create(name: 'Luke', movie: movies.first)

Usuario.create(email: "nsatragno@gmail.com", password: "12345678", nombre: "Nina Satragno",
               es_admin: true, es_gestion: true, es_tags: true, es_cajera: true)

Supermercado.create(nombre: "Supermercado SmartCart")

bebidas = Categoria.create(nombre: "Bebidas")
tipos_bebidas = ["Coca Cola", "Sprite", "Pepsi", "7 Up", "Aquarius Pomelo", "Aquarius Limón",
                 "Aquarius Pera", "Coca Cola Zero", "Coca Cola Light", "Pepsi Black",
                 "Levité Naranja", "Levité Pomelo", "Levité Pera"]
volumenes = ["353ml", "600ml", "1.5L", "2.4L"]

tipos_bebidas.each do |bebida|
  volumenes.each_with_index do |volumen, index|
    precio = rand(index * 10 + 1..index * 20 + 1)
    Producto.create(nombre: "#{bebida} #{volumen}",
                    apto_celiacos: false,
                    apto_diabeticos: false,
                    precio: precio,
                    categoria: bebidas)
  end
end

tipos_aguas = ["Eco de los Andes", "Villavicencio", "Evian", "IVESS", "Sierra de los Padres", "Cimes", "San Pellegrino"]
tipos_aguas.each do |bebida|
  volumenes.each_with_index do |volumen, index|
    precio = rand(index * 7 + 1..index * 10 + 1)
    Producto.create(nombre: "Agua Mineral #{bebida} #{volumen}",
                    apto_celiacos: true,
                    apto_diabeticos: true,
                    precio: precio,
                    categoria: bebidas)
  end
end

frutas = Categoria.create(nombre: "Frutas")
tipos_frutas = ["Frutilla", "Manzana", "Durazno", "Pera", "Ciruela", "Arándano",
                "Banana", "Cereza", "Ananá"]
pesos = ["0.5kg", "1kg", "3kg"]
tipos_frutas.each do |fruta|
  pesos.each_with_index do |peso, index|
    precio = rand(index * 3 + 1..index * 9 + 1)
    Producto.create(nombre: "#{fruta} x #{peso}",
                    apto_celiacos: true,
                    apto_diabeticos: true,
                    precio: precio,
                    categoria: frutas)
  end
end

dulces = Categoria.create(nombre: "Dulces")
marcas_mermelada = ["Dulciora", "BC", "Canale", "Alco", "La Campagnola"]
tipos_frutas.each do |fruta|
  marcas_mermelada.each_with_index do |marca, index|
    precio = rand(index * 30 + 1..index * 40 + 1)
    Producto.create(nombre: "Mermelada #{marca} de #{fruta}",
                    apto_celiacos: false,
                    apto_diabeticos: false,
                    precio: precio,
                    categoria: dulces)
  end
end

lacteos = Categoria.create(nombre: "Lácteos")
marcas_lacteos = ["La Serenísima", "La Rodriguense", "La Paulina", "Verónica", "SanCor"]
marcas_lacteos.each do |marca|
  Producto.create(nombre: "Dulce de Leche #{marca}",
                  apto_celiacos: true,
                  apto_diabeticos: false,
                  precio: rand(10..30),
                  categoria: dulces)
  volumenes.each_with_index do |volumen, index|
    precio = rand(index * 20 + 1..index * 40 + 1)
    Producto.create(nombre: "Leche #{marca} x #{volumen}",
                    apto_celiacos: true,
                    apto_diabeticos: false,
                    precio: precio,
                    categoria: lacteos)

    tipos_frutas.each do |fruta|
      Producto.create(nombre: "Yoghurt de #{fruta} #{marca} x #{volumen}",
                      apto_celiacos: true,
                      apto_diabeticos: false,
                      precio: precio,
                      categoria: lacteos)
    end
  end
end

harinas = Categoria.create(nombre: "Harinas")
tipos_panes = ["Porteñito", "Figazza", "Flautita", "Pebete"]
tipos_panes.each do |pan|
  pesos.each_with_index do |peso, index|
    precio = rand(index * 20 + 1..index * 30 + 1)
    Producto.create(nombre: "Pan #{pan} x #{peso}",
                    apto_celiacos: false,
                    apto_diabeticos: false,
                    precio: precio,
                    categoria: harinas)
  end
end

Producto.create(nombre: "Medialunas x 12u",
                apto_celiacos: false,
                apto_diabeticos: false,
                precio: 90,
                categoria: harinas)

sabores = ["Vainilla", "Chocolate", "Dulce de Leche"]
sabores.each do |sabor|
  Producto.create(nombre: "Base para Torta de #{sabor}",
                  apto_celiacos: false,
                  apto_diabeticos: false,
                  precio: 60,
                  categoria: harinas)
  Producto.create(nombre: "Bizcochuelo de #{sabor}",
                  apto_celiacos: false,
                  apto_diabeticos: false,
                  precio: 80,
                  categoria: harinas)
end


Chango.create(codigo: "Chango por Defecto")
