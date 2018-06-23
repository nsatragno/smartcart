class Categoria < ApplicationRecord
  self.table_name = "categorias"
  has_many :productos, dependent: :destroy

  validates :nombre, presence: true

  default_scope {
    order :nombre
  }
end
