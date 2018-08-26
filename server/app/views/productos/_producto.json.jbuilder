json.extract! producto, :id, :nombre, :descripcion, :precio, :apto_celiacos,
                        :apto_diabeticos
json.categoria do
  json.partial! 'categorias/categoria', categoria: producto.categoria
end

if producto.imagen.present?
  json.url imagen_producto_url(producto, format: :json)
end
