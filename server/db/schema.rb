# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 2018_09_01_213518) do

  create_table "categorias", force: :cascade do |t|
    t.string "nombre"
    t.float "posicion_x"
    t.float "posicion_y"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "changos", force: :cascade do |t|
    t.string "codigo"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "productos", force: :cascade do |t|
    t.string "nombre"
    t.decimal "precio"
    t.string "imagen_file_name"
    t.string "imagen_content_type"
    t.integer "imagen_file_size"
    t.datetime "imagen_updated_at"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.string "descripcion"
    t.boolean "apto_celiacos"
    t.boolean "apto_diabeticos"
    t.integer "categoria_id"
    t.index ["categoria_id"], name: "index_productos_on_categoria_id"
  end

  create_table "promociones", force: :cascade do |t|
    t.string "nombre"
    t.string "imagen_file_name"
    t.string "imagen_content_type"
    t.integer "imagen_file_size"
    t.datetime "imagen_updated_at"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "supermercados", force: :cascade do |t|
    t.string "nombre"
    t.string "plano_file_name"
    t.string "plano_content_type"
    t.integer "plano_file_size"
    t.datetime "plano_updated_at"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "tags", force: :cascade do |t|
    t.string "rfid"
    t.integer "producto_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.integer "chango_id"
    t.integer "estado", default: 0
    t.index ["chango_id"], name: "index_tags_on_chango_id"
    t.index ["producto_id"], name: "index_tags_on_producto_id"
  end

  create_table "usuario_apps", force: :cascade do |t|
    t.string "nombre"
    t.string "apellido"
    t.string "email", default: "", null: false
    t.string "encrypted_password", default: "", null: false
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["email"], name: "index_usuario_apps_on_email", unique: true
  end

  create_table "usuarios", force: :cascade do |t|
    t.string "email", default: "", null: false
    t.string "encrypted_password", default: "", null: false
    t.string "reset_password_token"
    t.datetime "reset_password_sent_at"
    t.datetime "remember_created_at"
    t.integer "sign_in_count", default: 0, null: false
    t.datetime "current_sign_in_at"
    t.datetime "last_sign_in_at"
    t.string "current_sign_in_ip"
    t.string "last_sign_in_ip"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.string "nombre"
    t.boolean "es_admin"
    t.boolean "es_gestion"
    t.boolean "es_tags"
    t.boolean "es_cajera"
    t.index ["email"], name: "index_usuarios_on_email", unique: true
    t.index ["reset_password_token"], name: "index_usuarios_on_reset_password_token", unique: true
  end

end
