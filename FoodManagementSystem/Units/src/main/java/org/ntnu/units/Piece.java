package org.ntnu.units;

public class Piece extends Unit {
	Piece(float value){
		this.value = value;
		this.unitName = "Piece";
	}

	@Override
	public String toString(){
		if (this.value > 1 || this.value < 1){
			return String.format("%f pieces", this.value);
		}
		return String.format("%f piece", this.value);
	}
}
