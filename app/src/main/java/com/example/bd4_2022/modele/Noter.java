package com.example.bd4_2022.modele;

public class Noter {
    private long idJ,idV,note;

    public Noter(long idJ, long idV, long note) {
        this.idJ = idJ;
        this.idV = idV;
        this.note = note;
    }

    public long getIdJ() {
        return idJ;
    }

    public void setIdJ(long idJ) {
        this.idJ = idJ;
    }

    public long getIdV() {
        return idV;
    }

    public void setIdV(long idV) {
        this.idV = idV;
    }

    public long getNote() {
        return note;
    }

    public void setNote(long note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Noter{" +
                "idJ=" + idJ +
                ", idV=" + idV +
                ", note=" + note +
                '}';
    }
}
