# ANTLR Syntax Checker

Это приложение на Java, использующее Spring Boot и ANTLR, для проверки синтаксических ошибок в Java-коде. Приложение принимает Java-код в формате JSON и возвращает список синтаксических ошибок, если они есть.

## Стек технологий
- Java 17
- Spring Boot
- ANTLR
- Maven
- Docker (опционально)

## Установка и запуск

### Запуск через Docker

1. **Сборка Docker-образа**:
   Выполните команду:
   ```bash
   docker build -t antlr-syntax-checker .

2. **Запуск контейнера**:
   Запустите контейнер с пробросом порта:
   ```bash
   docker run -p {{ваш порт}}:8080 antlr-syntax-checker

### Пример использования
1. **Отправьте запрос ```POST``` к ```http://localhost:{ваш порт}/checkSyntax```**.

2. **В теле запроса укажите Java-код, который хотите проверить**:
   ```json
   {
      "code": "public class Test { public void method() { int x = 10+ ; double y = 0.1 } }"
   }
3. **Получите результат с возможными синтаксическими ошибками**:
   ```json
   {
      "isValid": false,
      "errors": [
          "line 1:55 extraneous input ';' expecting {'boolean', 'byte', 'char', 'double', 'float', 'int', 'long', 'new', 'short', 'super', 'switch', 'this', 'void', 'module', 'open', 'requires', 'exports', 'opens', 'to', 'uses', 'provides', 'with', 'transitive', 'var', 'yield', 'record', 'sealed', 'permits', DECIMAL_LITERAL, HEX_LITERAL, OCT_LITERAL, BINARY_LITERAL, FLOAT_LITERAL, HEX_FLOAT_LITERAL, BOOL_LITERAL, CHAR_LITERAL, STRING_LITERAL, TEXT_BLOCK, 'null', '(', '<', '!', '~', '++', '--', '+', '-', '@', IDENTIFIER}",
          "line 1:66 no viable alternative at input 'double y ='",
          "line 1:72 missing ';' at '}'"
      ]
   }