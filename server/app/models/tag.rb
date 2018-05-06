class Tag < ApplicationRecord
  belongs_to :producto

  validates :rfid, presence: true
  validates :producto, presence: true
end
