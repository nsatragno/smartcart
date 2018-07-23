class Chango < ApplicationRecord
  has_many :tags

  def en_uso?
    tags.any? and tags.find do |tag| tag.disponible? end
  end

  def productos
    tags.map do |tag|
      tag.producto
    end.to_set
  end

  def productos_with_cantidad
    productos.map do |producto|
      {
        producto: producto,
        cantidad: tags.find_all do |tag|
          tag.producto == producto
        end.size
      }
    end
  end

  def total
    tags.sum do |tag|
      tag.producto.precio
    end
  end
end
