# Game Pong

Uma implementação de Pong desenvolvida em Java utilizando AWT.

O projeto foi criado com o objetivo de desenvolver um jogo 2D simples, colocando em prática conceitos de programação orientada a objetos, game loop, renderização e entrada de teclado.

## Sobre o projeto

O jogo utiliza o kit AWT do Java para criar a janela, receber entradas do teclado e realizar a renderização dos elementos.

A estrutura atual do projeto separa os principais elementos do jogo em classes próprias:

- `Pong.java` — classe principal e gerenciamento do jogo.
- `Player.java` — representa o jogador e controla sua movimentação.
- `Enemy.java` — representa o adversário.
- `Bola.java` — controla a bola e seu comportamento.
- `UI.java` — responsável pelos elementos da interface.

## Tecnologias

- Java
- AWT
- Swing

<p align="center">
  <img src="https://github.com/user-attachments/assets/91f467f0-afb2-481c-9389-d52bb5424a7a" width="45%" />
  <img src="https://github.com/user-attachments/assets/796ed3d0-179b-43a1-81ce-6f50a6bb7d38" width="45%" />
</p>
## Estrutura

```text
Game-Pong/
│
├── Bola.java
├── Enemy.java
├── Player.java
├── Pong.java
├── UI.java
└── README.md

## Como Jogar

O objetivo é controlar a sua raquete e rebater a bola para o lado do adversário.

### Controles

| Tecla | Ação |
|-------|------|
| `←` | Mover para a esquerda |
| `→` | Mover para a direita |

### Regras

- Controle o jogador utilizando as teclas `←` e `→`.
- Rebata a bola utilizando a raquete.
- Evite deixar a bola ultrapassar o seu lado da arena.
- A bola deve ser direcionada para o lado do adversário.
- O jogo continua enquanto a partida estiver ativa.

### Iniciando uma partida

1. Execute o jogo.
2. Aguarde a janela do Pong abrir.
3. Utilize `←` e `→` para movimentar o jogador.
4. Rebata a bola e tente mantê-la em jogo.

## Como instalar

### Pré-requisitos

Antes de executar o jogo, você precisa ter instalado:

- Java JDK 8 ou superior
- Git

### Clonando o repositório

Clone o projeto utilizando:

```bash
git clone https://github.com/psppGui/Game-Pong
