class AgregarEstadoATag < ActiveRecord::Migration[5.2]
  def change
    add_column :tags, :estado, :integer, default: 0
  end
end
