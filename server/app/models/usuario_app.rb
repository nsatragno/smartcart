class UsuarioApp < ApplicationRecord
  # Include default devise modules. Others available are:
  # :confirmable, :lockable, :timeoutable and :omniauthable
  devise :database_authenticatable, :registerable,
         :validatable

  validates :nombre, presence: true
  validates :apellido, presence: true
end
