class CreateSupermercados < ActiveRecord::Migration[5.2]
  def change
    create_table :supermercados do |t|
      t.string :nombre
      t.attachment :plano

      t.timestamps
    end
  end
end
