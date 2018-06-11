class Tag < ApplicationRecord
  belongs_to :producto
  belongs_to :chango

  validates :rfid, presence: true
  validates :producto, presence: true
end
