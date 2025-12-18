# Como Adicionar Imagens ao README

## Passo a Passo

### 1. Cole suas imagens na pasta
Cole seus prints de tela (imagens) na pasta:
```
docs/images/
```

### 2. Formato das imagens
- **PNG** (recomendado para screenshots)
- JPG/JPEG
- GIF
- SVG

### 3. Nomeie os arquivos
Use nomes descritivos e sem espaços:
- ✅ `tela-principal.png`
- ✅ `tela-historico.png`
- ✅ `resultados-calculo.png`
- ❌ `tela principal.png` (evite espaços)
- ❌ `IMG_1234.png` (use nomes descritivos)

### 4. Adicione no README.md
No arquivo `README.md`, use o formato:

```markdown
![Descrição da imagem](docs/images/nome-do-arquivo.png)
```

**Exemplo:**
```markdown
![Tela Principal do Aplicativo](docs/images/tela-principal.png)
```

### 5. Commit e Push
Depois de adicionar as imagens:

```bash
git add docs/images/
git commit -m "docs: Adicionar screenshots do aplicativo"
git push origin main
```

## Exemplos Práticos

### Imagem simples
```markdown
![Tela Principal](docs/images/tela-principal.png)
```

### Imagem com link (opcional)
```markdown
[![Tela Principal](docs/images/tela-principal.png)](docs/images/tela-principal.png)
```

### Imagem com tamanho (HTML - se necessário)
```html
<img src="docs/images/tela-principal.png" alt="Tela Principal" width="300">
```

## Dicas

1. **Tamanho**: Imagens muito grandes podem deixar o README pesado. Considere redimensionar se necessário.
2. **Organização**: Mantenha todas as imagens em `docs/images/`
3. **Nomes**: Use nomes consistentes e descritivos
4. **Formato**: PNG geralmente funciona melhor para screenshots

## Estrutura de Pastas

```
CalculadoradeIMC/
├── docs/
│   ├── images/
│   │   ├── tela-principal.png
│   │   ├── tela-historico.png
│   │   └── resultados.png
│   └── COMO_ADICIONAR_IMAGENS.md
└── README.md
```

