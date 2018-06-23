# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rails db:seed command (or created alongside the database with db:setup).
#
# Examples:
#
#   movies = Movie.create([{ name: 'Star Wars' }, { name: 'Lord of the Rings' }])
#   Character.create(name: 'Luke', movie: movies.first)

Usuario.create(email: "nsatragno@gmail.com", password: "12345678", nombre: "Nina Satragno",
               es_admin: true, es_gestion: true, es_tags: true, es_consulta: true)

Supermercado.create(nombre: "Supermercado SmartCart")

bebidas = Categoria.create(nombre: "Bebidas")
dulces = Categoria.create(nombre: "Dulces")

Producto.create(nombre: "Agua Mineral", descripcion: "Agua Fría Como El Hielo", apto_celiacos: true, apto_diabeticos: true, precio: 10, categoria: bebidas)
Producto.create(nombre: "Dulce de Leche", descripcion: "El mejor dulce", apto_celiacos: true, apto_diabeticos: false, precio: 10, categoria: dulces)

Chango.create(codigo: "Chango por Defecto")
