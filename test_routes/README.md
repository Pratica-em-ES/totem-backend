# Testador de Rotas - Totem Backend

Script Python para testar e visualizar as rotas calculadas pelo backend do Totem Interativo.

## 🚀 Funcionalidades

- ✅ Testa o endpoint `/routes/{fromBuildingId}?toBuildingId={toBuildingId}`
- 📊 Visualiza graficamente o mapa completo com o caminho destacado
- 🎯 Destaca prédios de origem/destino e seus pontos de entrada (edge_nodes)
- 🧪 Casos de teste pré-definidos
- 🔄 Modo interativo para testar rotas customizadas
- 💾 Salva imagens dos gráficos gerados

## 📋 Pré-requisitos

- Python 3.8+
- Backend rodando em `http://localhost:8080`

## 🔧 Instalação

```powershell
# Instalar dependências
pip install -r requirements.txt
```

## 📖 Como Usar

### Executar o script

```powershell
python test_routes.py
```

### Opções disponíveis

1. **Casos de teste pré-definidos**: Executa 6 testes interessantes automaticamente
2. **Modo interativo**: Permite escolher origem e destino manualmente
3. **Ambos**: Executa os casos de teste e depois entra no modo interativo

## 🧪 Casos de Teste Incluídos

1. **Tecnopuc → 99 A** (prédios próximos)
2. **Tecnopuc → 93** (longa distância)
3. **91 B → 91 A** (prédios da mesma série)
4. **97 → 95 C** (prédios próximos)
5. **96 E/H/I/G → Tecnopuc** (extremos do campus)
6. **94 → 92 A** (diagonal)

## 📊 Visualização

O gráfico gerado mostra:

- **Cinza claro**: Todos os nós e arestas do mapa
- **Azul**: Caminho calculado pelo algoritmo
- **Verde**: Prédio de origem
- **Vermelho**: Prédio de destino
- **Verde claro (quadrado)**: Ponto de entrada da origem
- **Vermelho claro (quadrado)**: Ponto de entrada do destino
- **Linhas tracejadas**: Conexão prédio → ponto de entrada

## 📁 Arquivos Gerados

Os gráficos são salvos como:
- `rota_{from_id}_to_{to_id}.png`

Exemplo: `rota_1_to_12.png` (Tecnopuc → 93)

## 🏢 Prédios Disponíveis

| ID | Nome           | ID | Nome           |
|----|----------------|----|----------------|
| 1  | Tecnopuc       | 8  | 96 J           |
| 2  | 99 A           | 9  | 96 E/H/I/G     |
| 3  | 95 A           | 10 | 91 B           |
| 4  | 97             | 11 | 94             |
| 5  | 95 C           | 12 | 93             |
| 6  | 96 A           | 13 | 92 A           |
| 7  | 96 B/C/D/F     | 14 | 91 A           |

## 🐛 Troubleshooting

### Erro de conexão
- Verifique se o backend está rodando: `http://localhost:8080`
- Teste manualmente: `curl http://localhost:8080/routes/1?toBuildingId=2`

### Módulos não encontrados
```powershell
pip install -r requirements.txt
```

### Gráficos não aparecem
- No Windows, pode ser necessário instalar: `pip install pyqt5`

## 💡 Exemplos de Uso

### Modo automático
```powershell
python test_routes.py
# Escolher opção 1
```

### Modo interativo
```powershell
python test_routes.py
# Escolher opção 2
# Digitar IDs dos prédios quando solicitado
```

## 🎨 Personalização

Para adicionar novos casos de teste, edite a lista `test_cases` em `run_test_cases()`:

```python
test_cases = [
    (1, 2, "Descrição do teste"),
    # Adicione mais aqui...
]
```

## 📝 Notas

- O script usa os dados do `import.sql` para plotar o mapa completo
- As coordenadas são extraídas diretamente do banco de dados
- O caminho é obtido via API REST do backend
