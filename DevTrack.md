# TODO

por Wendel Silvério

- - -

- [x] Refatoração do código para simplificar extração da expressão
    - [x] Substituição do método removeIllegalCharacters pelo replace
    - [x] Refatoração na validação de parênteses, colchetes e chaves.
    - [x] Extração de métodos de definição de operadores para a classe OperatorFactory
    - [x] Identificar como os objetos Number, Variable e Operator são definidos

- [x] Criação do helper MathEvaluator que retorna um string com o resultado ao invés de um double
	- http://stackoverflow.com/questions/9898512/how-to-test-if-a-double-is-an-integer

- [ ] Criação do repositório de fórmulas
	- [x] Ao adicionar a exceção de FormulaNotFound é necessário refatorar as exceptions, mas a exceção foi removida porque é mais fácil tratar um resultado null do que uma exceção.
	- [x] Persistindo as fórmulas em arquivo
    	- Não funcionou o SpringBoot então a lsita de fórmulas foi serializada.
    	- Foi criada a classe Serializator baseado em https://www.tutorialspoint.com/java/java_serialization.htm
    	- O arquivo foi salvo na pasta do usuário baseado em http://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
	- [ ] Salvar via REST 
