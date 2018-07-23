class Tag < ApplicationRecord
  enum estado: [:disponible, :pagado]

  belongs_to :producto
  belongs_to :chango, optional: true

  validates :rfid, presence: true
  validates :producto, presence: true
end
