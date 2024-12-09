package DTO;

public enum LoaiVe {
    VECANHAN,
    VETAPTHE;

    @Override
    public String toString() {
        if(this.equals(LoaiVe.VECANHAN)){
            return "Vé cá nhân";
        }
        if(this.equals(LoaiVe.VETAPTHE)){
            return "Vé tập thể";
        }
        return super.toString();
    }

    public String toStringSQL(){
        return super.toString();
    }
}
