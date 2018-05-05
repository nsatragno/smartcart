class CreateProductos < ActiveRecord::Migration[5.2]
  def change
    create_table :productos do |t|
      t.string :nombre
      t.decimal :precio
      t.attachment :imagen

      t.timestamps
    end
  end
end
