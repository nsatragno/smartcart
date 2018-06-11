class AsociarTagAChango < ActiveRecord::Migration[5.2]
  def change
    add_reference :tags, :chango
  end
end
