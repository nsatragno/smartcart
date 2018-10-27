json.extract! @supermercado, :id, :nombre
if @supermercado.plano.present?
  json.plano asset_url(@supermercado.plano.url)
else
  json.plano nil
end
