json.extract! producto, :id, :nombre, :precio, :imagen, :created_at, :updated_at
json.url producto_url(producto, format: :json)
