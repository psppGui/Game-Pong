# Game Pong

Um jogo inspirado no clássico Pong desenvolvido em Java utilizando AWT e Swing.

O projeto implementa uma partida entre o jogador e um adversário controlado pelo computador, com movimentação das raquetes, física básica da bola, detecção de colisões e sistema de pontuação.

## Sobre o projeto

O objetivo deste projeto é reproduzir a mecânica básica do Pong utilizando recursos nativos do Java, principalmente as bibliotecas `java.awt` e `javax.swing`.

O jogo possui uma janela gráfica, uma raquete controlada pelo jogador, uma raquete controlada pelo inimigo e uma bola que se movimenta pela tela. A lógica do jogo é executada continuamente através de um loop responsável por atualizar os objetos e renderizar os elementos na tela.

## Tecnologias utilizadas

* Java
* AWT
* Swing
* Graphics
* Canvas
* BufferStrategy
* BufferedImage
* KeyListener

## Funcionalidades

* Janela gráfica utilizando Swing.
* Renderização utilizando `Canvas` e `Graphics`.
* Movimentação da raquete do jogador.
* Controle da raquete através das teclas direcionais.
* Adversário controlado automaticamente.
* Movimentação da bola.
* Detecção de colisão entre bola e raquetes.
* Colisão da bola com as bordas da tela.
* Sistema de pontuação.
* Reinício da bola após determinados eventos.
* Loop de jogo com atualização e renderização contínuas.

## Controles

| Tecla | Ação                                |
| ----- | ----------------------------------- |
| `←`   | Movimenta a raquete para a esquerda |
| `→`   | Movimenta a raquete para a direita  |

## Estrutura do projeto

```text
Game-Pong/
├── Bola.java
├── Enemy.java
├── Player.java
├── Pong.java
├── UI.java
└── README.md
```

### `Pong.java`

É a classe principal do jogo.

Ela é responsável por:

* Inicializar o jogo.
* Criar a janela.
* Inicializar os objetos do jogo.
* Executar o loop principal.
* Atualizar os elementos.
* Renderizar os elementos.
* Capturar os eventos do teclado.

O loop principal executa a atualização e a renderização aproximadamente 75 vezes por segundo.

### `Player.java`

Representa a raquete controlada pelo jogador.

A classe utiliza `KeyListener` para detectar as teclas direcionais e movimentar a raquete horizontalmente, mantendo-a dentro dos limites da tela.

### `Enemy.java`

Representa a raquete controlada pelo computador.

A posição da raquete é atualizada de acordo com a posição da bola, criando um comportamento básico de adversário automático.

### `Bola.java`

Responsável pela lógica da bola.

A classe controla:

* Posição da bola.
* Direção do movimento.
* Velocidade.
* Colisões com as paredes.
* Colisões com as raquetes.
* Reinicialização da bola.
* Pontuação dos jogadores.

As colisões entre a bola e as raquetes são verificadas utilizando `Rectangle.intersects()`.

### `UI.java`

Responsável pela interface visual do jogo.

A classe desenha:

* Pontuação do jogador.
* Pontuação do adversário.
* Linha central da arena.

## Como executar

### Pré-requisitos

É necessário ter o Java Development Kit (JDK) instalado.

Verifique a instalação executando:

```bash
java -version
javac -version
```

### Clonar o repositório

```bash
git clone https://github.com/psppGui/Game-Pong.git
cd Game-Pong
```

### Compilar

Compile os arquivos Java:

```bash
javac *.java
```

### Executar

Depois da compilação, execute a classe principal:

```bash
java Pong
```

Uma janela do jogo será aberta e a partida poderá ser iniciada.

## Funcionamento

O jogo utiliza um ciclo contínuo de atualização e renderização.

De forma simplificada, o funcionamento é:

```text
Inicialização
     |
     v
Criação da janela
     |
     v
Inicialização dos objetos
     |
     v
Loop do jogo
     |
     +----> Atualiza jogador
     |
     +----> Atualiza inimigo
     |
     +----> Atualiza bola
     |
     +----> Renderiza os objetos
     |
     v
Repete
```

A classe `Pong` centraliza os principais objetos do jogo e chama os métodos de atualização e renderização de cada componente.

## Resolução

A área interna do jogo utiliza uma resolução lógica de `300 x 200`, com escala de `4x`, resultando em uma janela de aproximadamente `1200 x 800` pixels.

## Pontuação

A pontuação é armazenada pela classe `Bola` e apresentada pela classe `UI`.

O placar é atualizado conforme a bola ultrapassa determinadas regiões da arena.

## Objetivo do projeto

Este projeto foi desenvolvido como uma implementação simples do clássico Pong, com foco no aprendizado de conceitos fundamentais de desenvolvimento de jogos em Java, como:

* Programação orientada a objetos.
* Eventos de teclado.
* Loop de jogo.
* Renderização gráfica.
* Detecção de colisões.
* Movimentação de objetos.
* Controle de estado.
* Manipulação de janelas com Swing.

## Possíveis melhorias

Algumas melhorias que podem ser implementadas futuramente:

* Adicionar uma tela inicial.
* Criar sistema de vitória e derrota.
* Adicionar opção de reiniciar a partida.
* Melhorar a inteligência artificial do adversário.
* Adicionar efeitos sonoros.
* Adicionar efeitos visuais.
* Permitir alteração da velocidade da bola.
* Criar diferentes níveis de dificuldade.
* Melhorar o sistema de colisão.
* Adicionar suporte para dois jogadores.
* Criar um sistema de pausa.
* Melhorar a organização do código.

## Autor

Desenvolvido por [psppGui](https://github.com/psppGui).

## Repositório

O código-fonte está disponível no GitHub:

https://github.com/psppGui/Game-Pong
