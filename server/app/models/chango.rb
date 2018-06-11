class Chango < ApplicationRecord
  has_many :tags

  def en_uso?
    tags.any?
  end
end
