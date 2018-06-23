class Supermercado < ApplicationRecord
  validates :nombre, presence: true
  has_attached_file :plano
  validates_attachment_content_type :plano, content_type: /\Aimage\/.*\z/
end
