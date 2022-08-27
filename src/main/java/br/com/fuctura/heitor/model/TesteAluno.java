package br.com.fuctura.heitor.model;

public class TesteAluno {

	public static void main(String[] args) {

		Aluno a1 = new Aluno(1l,"1234567901","Luan");
		Aluno a2 = new Aluno(2l,"1234567902","Ruan");
		Aluno a3 = new Aluno(3l,"1234567903","Juan");
		Aluno a4 = new Aluno(2l,"1234567902","Ruan");
		
		System.out.println(a1);
		System.out.println(a2);
		System.out.println(a3);
		
		if(a2.equals(a4)) {
			System.out.println("Aluno 2 é igual a Aluno 4");
		}else {
			System.out.println("Aluno 2 é diferente do Aluno 4");
		}
		
		System.out.println("Aluno 1:"+a1.hashCode());
		System.out.println("Aluno 2:"+a2.hashCode());
		System.out.println("Aluno 3:"+a3.hashCode());
		System.out.println("Aluno 4:"+a4.hashCode());
	}

}
