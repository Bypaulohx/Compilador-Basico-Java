# Compilador Básico em Java

## Descrição
Este projeto implementa um compilador simples em Java que transforma expressões matemáticas básicas em bytecode e as executa em uma máquina virtual stack-based.

## Funcionalidades
- Lexer (analisador léxico)
- Parser (analisador sintático)
- AST (árvore sintática abstrata)
- Gerador de Bytecode
- Máquina Virtual

## Arquitetura
```
program.toy -> Lexer -> Parser -> AST -> CodeGenerator -> Bytecode -> VirtualMachine -> Resultado
```

## Exemplo de uso
Código-fonte (`examples/program.toy`):
```
(3 + 5) * 2 - 4 / 2
```

Execução:
```sh
javac -d out src/main/java/compiler/*.java
java -cp out compiler.Main examples/program.toy
```

Saída esperada:
```
Bytecode gerado: [PUSH 3, PUSH 5, ADD, PUSH 2, MUL, PUSH 4, PUSH 2, DIV, SUB]
Resultado: 14
```

## Estrutura do projeto
- `src/main/java/compiler/` → Código-fonte Java
- `examples/` → Programas de exemplo
- `.vscode/` → Configurações para VSCode
- `README.md` → Documentação principal

## Execução no VSCode
- `Ctrl+Shift+B` → compila o projeto
- `F5` → roda o programa com o exemplo
