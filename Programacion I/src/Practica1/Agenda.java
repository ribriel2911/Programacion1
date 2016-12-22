package Practica1;

public class Agenda {
	
	Persona[] contactos;
	String[] telefonos;

	Agenda(int tamaño){
		
		this.contactos = new Persona[tamaño];
		this.telefonos = new String[tamaño];
	}
	
	void guardar(Persona contacto,String telefono){
		
		boolean hayLugar = false;
		
		for(int i=0;i<this.contactos.length;i++){
			
			if (contactos[i]!=null){
				
				contactos[i]=contacto;
				telefonos[i]=telefono;
				i=this.contactos.length;
				hayLugar=true;
			}
		}
			
		if(!hayLugar){
			
			Agenda masGrande = new Agenda(this.contactos.length+1);
			
			for (int i2=0; i2<masGrande.contactos.length;i2++){
				
				if(i2<this.contactos.length){
					
					masGrande.contactos[i2] = this.contactos[i2];
					masGrande.telefonos[i2] = this.telefonos[i2];
				}
				
				else{
					
					masGrande.contactos[i2] = contacto;
					masGrande.telefonos[i2] = telefono;
				}
			}
			
			this.contactos = masGrande.contactos;
			this.telefonos = masGrande.telefonos;
		}
	}
	
	void eliminar(Persona contacto){
		
		for (int i=0;i<this.contactos.length;i++){
			
			if (this.contactos[i].equals(contacto)){
				
				this.contactos[i]=null;
				this.telefonos[i]=null;
				i=this.contactos.length;
			}
		}
	}
	
	boolean pertenece(Persona contacto){
		
		for (int i=0;i<this.contactos.length;i++){
			
			if (this.contactos[i].equals(contacto)){
				
				return true;
			}
		}
		
		return false;
	}
	
	String dameTelefono(Persona contacto){
		
		if(pertenece(contacto)){
			
			for (int i=0;i<this.contactos.length;i++){
				
				if(contactos[i].mismaPersona(contacto)){
					
					return telefonos[i];
				}
			}
		}
		
		return null;
	}
}

