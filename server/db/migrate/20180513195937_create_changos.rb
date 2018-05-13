class CreateChangos < ActiveRecord::Migration[5.2]
  def change
    create_table :changos do |t|
      t.string :codigo

      t.timestamps
    end
  end
end
