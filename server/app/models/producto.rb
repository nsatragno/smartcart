require 'identicon'

class Producto < ApplicationRecord
  has_attached_file :imagen
  belongs_to :categoria

  validates_attachment_content_type :imagen, content_type: /\Aimage\/.*\z/

  validates :nombre, presence: true
  validates :precio, presence: true

  default_scope {
    order :nombre
  }

  before_save :generate_image

  private

  def generate_image
    return if imagen.present?

    uri_imagen = "#{Rails.root}/tmp/imagenes/#{id}_#{nombre}"
    carpeta = File.dirname(uri_imagen)
    FileUtils.mkdir_p(carpeta) unless File.directory?(carpeta)

    Identicon.file_for nombre, uri_imagen
    File.open(uri_imagen) do |file|
      self.imagen = file
    end
  end
end
