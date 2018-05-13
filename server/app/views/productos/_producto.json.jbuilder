json.extract! producto, :id, :nombre, :precio
json.url imagen_producto_url(producto, format: :json)
