class AgregarDetallesAProductos < ActiveRecord::Migration[5.2]
  def change
    add_column :productos, :descripcion, :string
    add_column :productos, :apto_celiacos, :boolean
    add_column :productos, :apto_diabeticos, :boolean
  end
end
