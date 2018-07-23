class CajasController < ApplicationController
  before_action :authenticate_usuario!
  before_action :validar_cajera

  def index
    @changos = Chango.all.find_all do |chango|
      chango.en_uso?
    end
  end

  def edit
    @chango = Chango.find(params[:id])
  end

  def update
    Tag.transaction do
      tag_ids = params[:chango][:tags].map do |tag|
        Tag.find tag.to_i
      end.each do |tag|
        tag.pagado!
        tag.save!
      end
    end

    flash[:success] = "Chango cobrado con éxito"
    redirect_to action: "index"
  end
end
