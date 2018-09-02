json.extract! chango, :id, :codigo

json.productos do
  json.array! chango.productos_with_cantidad do |producto|
    json.producto producto[:producto], partial: 'productos/producto', as: :producto
    json.cantidad producto[:cantidad]
  end
end
