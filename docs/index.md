# Documentação - Sistema Byte & Brew

Este documento descreve a infraestrutura de software, a persistência de dados em memória e a estratégia de arquitetura de serviços implementadas na versão atual do sistema de backend da cafeteria Byte & Brew (baseado em Java SE).



---

# Sumário

* [Modelagem Arquitetural no draw.io](#modelagem-arquitetural-no-drawio)
* [Arquitetura de Software e Divisão de Pacotes](#arquitetura-de-software-e-divisão-de-pacotes)
    * [Pacote cafeteria.modelo](#pacote-cafeteriamodelo)
        * [Classe Produto (Abstração e Encapsulamento)](#classe-produto-abstração-e-encapsulamento)
        * [Classe ProdutoRepository (Persistência e Coleções)](#classe-produtorepository-persistência-e-coleções)
        * [Classe Comida (Herança e Atributos Específicos)](#classe-comida-herança-e-atributos-específicos)
        * [Classe Bebida (Herança e Atributos Específicos)](#classe-bebida-herança-e-atributos-específicos)
        * [Classe Cliente (Classe Abstrata e Modelagem)](#classe-cliente-classe-abstrata-e-modelagem)
        * [Classe ClienteStandard (Herança e Polimorfismo)](#classe-clientestandard-herança-e-polimorfismo)
        * [Classe ClienteVip (Herança e Regras de Fidelidade)](#classe-clientevip-herança-e-regras-de-fidelidade)
        * [Classe Atendente (Entidade e Associação)](#classe-atendente-entidade-e-associação)
        * [Classe ItemPedido (Associação e Agregação)](#classe-itempedido-associação-e-agregação)
        * [Classe Pedido (Encapsulamento e Composição de Estado)](#classe-pedido-encapsulamento-e-composição-de-estado)
    * [Pacote cafeteria.servico](#pacote-cafeteriaservico)
        * [Interface Promocional (Polimorfismo e Contratos)](#interface-promocional-polimorfismo-e-contratos)
        * [Classe GerenciadorPedido (Malha de Serviço)](#classe-gerenciadorpedido-malha-de-serviço)
    * [Pacote cafeteria.excecao](#pacote-cafeteriaexcecao)
        * [Classe EstoqueInsuficienteException (Robustez e Robustez)](#classe-estoqueinsuficienteexception-robustez-e-robustez)
    * [Pacote cafeteria.app](#pacote-cafeteriaapp)
        * [Classe Main (Bootstrap e Testes)](#classe-main-bootstrap-e-testes)
* [Exemplo de Implementação (Código do Backend)](#exemplo-de-implementação-código-do-backend)
* [Histórico de Versões](#histórico-de-versões)

---

# Modelagem Arquitetural no draw.io

Para a concepção estrutural e mapeamento de dependências do ecossistema Byte & Brew, utilizamos o **draw.io** (diagrams.net) como ferramenta oficial de modelagem UML. 

Através do draw.io, estruturamos visualmente a divisão de pacotes, os modificadores de acesso (`private` e `public`), a herança das subclasses e as relações de dependência entre as regras de negócio e o banco de dados em memória. O diagrama final gerado na ferramenta foi exportado e serve como o gabarito estrito para a implementação do código Java.

---

# Arquitetura de Software e Divisão de Pacotes

Abaixo encontra-se o detalhamento de cada pacote implementado no sistema, isolando as responsabilidades e descrevendo quais pilares da Orientação a Objetos foram aplicados em cada classe.

## Pacote cafeteria.modelo

### Classe Produto (Abstração e Encapsulamento)
* **O que foi aplicado:** * **Abstração:** A classe mapeia os atributos essenciais de um item real (código, nome, preço e estoque). No desenho arquitetural, ela foi definida como **abstrata** (`abstract`), servindo puramente de molde genérico, impedindo instanciar um produto que não seja especificamente uma Bebida ou Comida.
    * **Encapsulamento Severo:** Todos os atributos foram blindados como `private`. O acesso e leitura desses estados internos são permitidos exclusivamente por portas controladas (métodos Getters). Não foram expostos métodos *Setters* genéricos para garantir a imutabilidade do código e do preço após o cadastro.
    * **Lógica de Guarda:** O método `baixarEstoque(int quantidade)` implementa uma validação interna que impede que o estoque assuma valores negativos, protegendo a integridade dos dados da aplicação.

### Classe ProdutoRepository (Persistência e Coleções)
* **O que foi aplicado:** * **Encapsulamento de Infraestrutura:** Isola o acesso direto aos dicionários de dados. Quem precisa salvar ou buscar um produto não sabe (e não precisa saber) que o sistema usa um `HashMap` por baixo dos panos.
    * **Uso de Memória Estática:** Os mapas de dados foram definidos com o modificador `static`, garantindo que os dados persistidos sejam globais e compartilhados por todo o ciclo de vida da aplicação, simulando de forma idêntica um banco de dados unificado.

### Classe Comida (Herança e Atributos Específicos)
* **O que foi aplicado:** * Herança (extends Produto): Herda todas as características básicas de Produto (código, nome, preço base), evitando a duplicação de código. Adiciona os atributos específicos do seu domínio de negócio, como tempoPreparoMinutos e as marcações booleanas isVegano e isGlutenFree.
  
### Classe Bebida (Herança e Atributos Específicos)
* **O que foi aplicado:** * Herança (extends Produto): Também estende a classe mãe Produto reutilizando toda a sua lógica de encapsulamento de dados, mas especializa o modelo contendo o atributo específico tamanho para diferenciar as porções servidas na cafeteria.

### Classe Cliente (Classe Abstrata e Modelagem)
* **O que foi aplicado:** A classe Cliente foi definida como abstrata para representar os dados e comportamentos comuns às especializações de cliente, como ClienteVIP e ClienteStandard. Dessa forma, atributos como nome, cpf e saldoXP ficam concentrados na superclasse Cliente, evitando a repetição de código em suas subclasses. Esses atributos foram declarados como privados, garantindo o encapsulamento dos dados, e seu acesso ocorre por meio de métodos públicos, como getters e setters definidos na classe. Além disso, a classe define comportamentos relacionados ao acúmulo e ao desconto de XP, que podem ser reutilizados ou sobrescritos pelas subclasses. Assim, a superclasse Cliente funciona como uma generalização, enquanto ClienteVIP e ClienteStandard representam especializações, organizando a hierarquia dos tipos de cliente no sistema.

### Classe ClienteStandard (Herança e Polimorfismo)
* **O que foi aplicado:** Assim como a classe ClienteVIP, a classe ClienteStandard representa uma especialização da classe abstrata Cliente, reutilizando atributos e métodos comuns definidos na superclasse. Nessa classe, foi aplicada a sobrescrita do método adicionarXP(), mantendo a regra padrão de fidelidade em que o cliente recebe a pontuação padrão no sistema. Assim, o ClienteStandard representa o cliente padrão, sem benefícios extras ou possibilidade de pagamento com XP.

### Classe ClienteVip (Herança e Regras de Fidelidade)
* **O que foi aplicado:** A classe ClienteVIP representa uma especialização da classe abstrata Cliente, reutilizando dados e comportamentos comuns definidos na superclasse Cliente. Nessa classe, foi aplicada a sobrescrita do método adicionarXP(),  permitindo que o ClienteVIP receba uma pontuação diferente no programa de fidelidade, quando comparado ao ClienteStandard. Além disso, há o método pagarXP(), que permite ao ClienteVIP utilizar seu saldo de XP como forma de pagamento. Dessa forma, a classe concentra regras específicas de fidelidade e benefícios exclusivos.

### Classe Atendente (Entidade e Associação)
* **O que foi aplicado:** * Encapsulamento: Representa os colaboradores operacionais do sistema. Guarda de forma privada dados como nome, cpf e matrícula, atuando como o elo humano associado diretamente à abertura de novos pedidos.

### Classe ItemPedido (Associação e Agregação)
* **O que foi aplicado:** * Classes, Objetos e Associações (Agregação). Funciona como uma classe associativa que conecta o pedido ao produto. Aplica o conceito de Agregação com a classe `Produto`, pois o produto existe de forma independente no sistema (se o item for removido do carrinho, o produto continua existindo no cardápio). Ela encapsula os atributos dinâmicos dessa união, como a quantidade e o subtotal do item.

### Classe Pedido (Encapsulamento e Composição de Estado)
* **O que foi aplicado:** * Composição, Agregação, Modificadores de Escopo e Polimorfismo (Inclusão, Sobrecarga e Coerção). É o coração do sistema e concentra múltiplas características. Aplica Composição na relação com `ItemPedido`, pois a existência dos itens depende estritamente do ciclo de vida do pedido (se o pedido for cancelado ou excluído, seus itens deixam de existir). Aplica Agregação com a classe `Cliente`, que possui existência independente. Utiliza Modificadores de Escopo (Estático) para gerar o número sequencial automático dos pedidos e para a constante de conversão de XP. Manifesta Polimorfismo por Inclusão ao gerenciar e processar a lista de itens de forma genérica, Polimorfismo por Sobrecarga ao oferecer duas assinaturas para o método `adicionarItem` (uma padrão com 1 unidade e outra que aceita uma quantidade específica), e Polimorfismo por Coerção nas operações matemáticas de fechamento e conversões conscientes de tipos numéricos.


## Pacote cafeteria.servico

### Interface Promocional (Polimorfismo e Contratos)
* **O que foi aplicado:** * **Contratos Limpos (Interfaces):** Não possui atributos nem corpos de método. Ela dita estritamente um comportamento obrigatório para o sistema.
    * **Polimorfismo:** Permite que diferentes tipos de regras de desconto (como o desconto do Dia Geek ou descontos de fidelidade) sejam tratados de forma genérica pelo sistema através do método `aplicarDesconto(double totalAtual)`. O sistema chama o método sem precisar saber qual classe específica está executando o cálculo.

### Classe GerenciadorPedido (Malha de Serviço)
* **O que foi aplicado:**
    * **Alta Coesão:** Desonera as classes de modelo de carregar lógica de processamento financeiro. Ela atua puramente como uma classe orquestradora de serviços, recebendo o objeto `Pedido` e comandando o seu fluxo de finalização.

## Pacote cafeteria.excecao

### Classe EstoqueInsuficienteException (Robustez e Reuso)
* **O que foi aplicado:**
    * **Herança (`extends Exception`):** Aplicação direta de herança para estender as capacidades da classe mãe nativa do Java. Com isso, a classe se torna uma *Checked Exception*, interrompendo o fluxo imediatamente caso um cliente tente comprar mais unidades do que o repositório possui.
    * **Reuso via Construtor (`super`):** O construtor utiliza o método `super(mensagem)` para repassar a string de erro diretamente para a infraestrutura de tratamento de exceções do Java.

## Pacote cafeteria.app

### Classe Main (Bootstrap e Testes)
* **O que foi aplicado:**
    * **Ponto de Entrada Inicial:** Responsável única por dar o arranque (*Bootstrap*) no sistema.
    * **Polimorfismo via Classe Anônima:** Utilizada para instanciar e validar localmente o comportamento da classe abstrata `Produto` e testar os métodos de CRUD do `ProdutoRepository` sem dependências externas das subclasses de negócio do grupo.

---

# Exemplo de Implementação (Código do Backend)

Mecânica do repositório modelada no draw.io e convertida para código Java:

```java
package cafeteria.modelo;

import java.util.HashMap;
import java.util.Map;

public class ProdutoRepository {
    private static Map<String, Integer> estoqueProdutos = new HashMap<>();
    private static Map<String, Produto> catalogoProdutos = new HashMap<>();
    
    public void salvar(Produto p, int quantidadeInicial) {
        catalogoProdutos.put(p.getCodigo(), p);
        estoqueProdutos.put(p.getCodigo(), quantidadeInicial);
    }
    
    public Produto buscarPorCodigo(String codigo) {
        return catalogoProdutos.get(codigo);
    }
    
    public int consultarEstoque(String codigo) {
        return estoqueProdutos.getOrDefault(codigo, 0);
    }
    
    public void atualizarEstoque(String codigo, int quantidade) {
        if(estoqueProdutos.containsKey(codigo)) {
            estoqueProdutos.put(codigo, quantidade);
        }
    }
}

```

---

# Histórico de Versões

| Versão | Descrição das Implementações | Autor(es) | Data de Produção | Status |
| --- | --- | --- | --- | --- |
| `1.0` | Modelagem arquitetural das classes via ferramenta draw.io | Seu Nome / Grupo | 27/06/2026 | ✓ |


```

```
