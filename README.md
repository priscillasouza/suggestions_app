# suggestions_app
Aplicativo que sugere atividades aleatórias para o usuário realizar.
[APK](https://github.com/priscillasouza/suggestions_app/blob/main/apk/app-suggestion.apk?raw=true) || [YOUTUBE](https://youtu.be/N9RutrQsE4g)


**Screen**
| **Home** |
|--|
| ![enter image description here](https://github.com/priscillasouza/suggestions_app/blob/main/assets/images/img.png) |


# Base do projeto
**Multimodulos:**
A aplicação foi dividida em 3 módulos:</br>
 * APP - módulo principal do aplicativo onde ficam todas as configurações. 
 * DATA - nesse modulo foi configurada a infraestrutura remota e local.</br>
 Remota: para fazer a conexão e coletar os dados da API foi utilizado o Retrofit.</br>
 Local: para armazenar os dados localmente foi utilizado o Room
 * ACTIVITY-SUGGESTION - configuração das camadas utilizando a arquitetura MVVM</br>
Onde foram implementadas as funcionalidades do aplicativo.

**Gradle:**</br>
O gradle foi configurado no módulo app utilizando o version catalogs, que centraliza as dependências e versões em um único arquivo e essas dependências são chamadas no gradle de cada módulo de acordo com o que cada um precisa.

**Multicamada:**</br>
Organização de código foi feita utilizando a arquitetura MVVM
 -Presentation: camada para gerenciar o acesso inicial aos dados através do ViewModel
 -Domain: camada para gerenciar regras de negócios
 -Data: camada para dados de acesso integrado usando Repository

**Databinding:**</br>
Foi utilizado o databing pois é uma maneira fácil de controlar as regras da interface do usuário e facilita a ligação de dados na tela.

**Navigation:**</br>
Foi criado gráfico de navegação no módulo activity-suggestion e esse gráfico foi incluído no gráfico de navegação do módulo app para rodar a aplicação com as informações. 

**Injeção de dependência:**</br>
Foi utilizado o Koin, uma biblioteca prática de injeção de dependências, o código não será associado e ainda será fácil resolver automaticamente as dependências no tempo de execução.

**Retrofit:**</br>
O retrofit foi utilizado para fazer a conexão com a API disponibilizada.
Utilizando o método GET foram realizadas as chamadas das atividades(activity) e tipo de atividade(type).

**Room:**</br>
O room database foi utilizado para salvar localmente os dados da atividade retornados da API e também para armazenar o status de progresso da atividade, a lista de atividades em andamento e a lista de atividades finalizadas.
Ações:
 - Ao clicar no botão aceitar, atualizado o status para "em andamento", é armazenado o tempo inicial da atividade, e essa atividade é apresentada na lista da recycler view de "atividades em andamento"
 - Ao clicar no botão desistir, o status é atualizado para "desistencia", é armazenado o time do momento da desistência, e a atividade sai da lista da recycler view em andamento.
 - Ao clicar no botão finalizar, o status é atualizado para "finalizada", a atividade sai da lista da recycler view em andamento, e a atividade vai para a lista da recycler view de "atividades finalizadas" e é realizado o cálculo de tempo gasto na atividade.

# Suggestion App
Eu como usuário gostaria de ter um aplicativo que:
- Seja possível visualizar as atividades aleatórias
- Seja possível filtrar uma atividade aleatória por tipo
- Seja possível rejeitar uma ativade
- Seja possível aceitar uma atividade
- Seja possível desistir de uma atividade
- Seja possível finalizar uma atividade
- Seja possível visualizar uma lista das atividades em andamento
- Seja possível visualizar uma lista das atividades finalizadas

O que deve conter nesse aplicativo : 
- Permitir que o usuário visualize as atividades aleatórias
- Permitir que o usuário filtre as atividades por tipo
- Permitir que o usuário rejeite uma atividade
- Permitir que o usuário aceite uma atividade
- Permitir que o usuário desista de uma atividade
- Permitir que o usuário finalize uma atividade
- Exibir o resultado da api para o usuário
- Permitir que usuário visualize uma lista das atividades em andamento
- Permitir que usuário visualize uma lista das atividades finalizadas

# Orientações para a construção do Suggestion App:
A APi disponibilizada foi a http://www.boredapi.com e através dela é possível buscar as atividades.

- GET http://www.boredapi.com/api/activity/
Retorna informações sobre a atividade.
Exemplo de resposta ->
GET
Response:
{
	"activity": "Learn a new programming language",
	"accessibility": 0.25,
	"type": "education",
	"participants": 1,
	"price": 0.1,
	"key": "5881028"
}

- GET http://www.boredapi.com//api/activity?type=:type
Retorna informações sobre o tipo de atividade.
Exemplo de resposta ->
Response:
{
	"activity": "Learn how to play a new sport",
	"accessibility": 0.2,
	"type": "recreational",
	"participants": 1,
	"price": 0.1,
	"key": "5808228"
}

# Débitos técnicos
Implementar o tempo gasto da atividade, entender melhor como lidar com conversões e cálculos com tipo Date.









