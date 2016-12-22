package Practica1;


public class Persona {



	String nombre;
	int edad;
	int dni;

	Persona(String nombre, int edad){

		
		this.nombre = nombre;
		this.edad = edad;
	}

	boolean masJovenQue(Persona otro){

		if (this.edad<otro.edad){

			return true;
		}
		
		return false;
	}

	boolean tocayo(Persona otro){

		if (this.nombre.equals(otro.nombre)){

			return true;
		}
		
		return false;
	}

	boolean mismaPersona(Persona otro){

		if (tocayo(otro)&&(this.edad==otro.edad)){

			return true;
		}

		return false;
	}

	boolean mismaPersona(int dni){

		if (dni==this.dni){

			return true;
		}

		return false;
	}
}

