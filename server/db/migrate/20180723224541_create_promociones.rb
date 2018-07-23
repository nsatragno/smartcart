class CreatePromociones < ActiveRecord::Migration[5.2]
  def change
    create_table :promociones do |t|
      t.string :nombre
      t.attachment :imagen

      t.timestamps
    end
  end
end
