class CreateCategoria < ActiveRecord::Migration[5.2]
  def change
    create_table :categorias do |t|
      t.string :nombre
      t.float :posicion_x
      t.float :posicion_y

      t.timestamps
    end
  end
end
