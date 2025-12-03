# CENÁRIO 1: Criar aluno com sucesso
**Pré-condições**: Não existir aluno cadastrado com o nome informado. <br>
**Massa de Teste**: nome = "Maria" <br>
**Resultado Esperado:**<br>
  a) Aluno deve ser salvo com sucesso.<br>
  b) Repositório deve ser chamado 1 vez para salvar.<br>
  c) Deve retornar um objeto Aluno com o nome padronizado (trim).<br>

# CENÁRIO 2: Criar exercício com sucesso
**Pré-condições:** não se aplica. <br>
**Massa de Teste:** <br>
  a) título = "Ex1" <br>
  b) descrição = "Descrição teste"<br>
**Resultado Esperado:** <br>
a) Exercício deve ser criado e salvo.<br>
b) Repositório deve ser chamado 1 vez.<br>

# CENÁRIO 3: Criar vínculo entre aluno e exercício
**Pré-condições:** <br>
a) O exercício existe. <br>
b) O aluno existe. <br>
**Massa de Teste:** <br>
a) exercicioId = 10 <br>
b) alunoId = 20 <br>
**Resultado Esperado:** <br>
a) Exercício deve receber o aluno. <br>
b) Repositório deve salvar o exercício atualizado. <br>
c) Deve retornar o exercício com o aluno vinculado.<br>

# CENÁRIO 4: Tentar criar aluno que já existe
**Pré-condições:** <br>
a) Um aluno com nome "Maria" já existe. <br>
**Massa de Teste:** <br>
a) nome = "Maria" <br>
**Resultado Esperado:** <br>
a) Deve lançar IllegalArgumentException. <br>
b) Mensagem: "Aluno já cadastrado" <br>
c) Nenhum save deve acontecer.<br>

# CENÁRIO 5: Excluir exercício
**Pré-condições:** <br>
a) Exercício existe no banco. <br>
**Massa de Teste:** <br>
a) exercicioId = 5 <br>
**Resultado Esperado:** <br>
a) deleteById deve ser chamado 1 vez. <br>
b) Nenhuma exceção deve ser lançada. <br>
