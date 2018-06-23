class AgregarCategoriaAProducto < ActiveRecord::Migration[5.2]
  def change
    add_reference :productos, :categoria
  end
end
