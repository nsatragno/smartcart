class Producto < ApplicationRecord
  has_attached_file :imagen

  validates_attachment_content_type :imagen, content_type: /\Aimage\/.*\z/

  validates :nombre, presence: true
  validates :precio, presence: true

  default_scope {
    order :nombre
  }
end
