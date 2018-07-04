class AgregarRolCajera < ActiveRecord::Migration[5.2]
  def change
    add_column :usuarios, :es_cajera, :boolean
    remove_column :usuarios, :es_consulta, :boolean
  end
end
