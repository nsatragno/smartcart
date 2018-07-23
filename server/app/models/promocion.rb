class Promocion < ApplicationRecord
  has_attached_file :imagen

  validates :nombre, presence: true
  validates :imagen, presence: true

  validates_attachment_content_type :imagen, content_type: /\Aimage\/.*\z/
end
