class AddNombreToUsuario < ActiveRecord::Migration[5.2]
  def change
    add_column :usuarios, :nombre, :string
  end
end
