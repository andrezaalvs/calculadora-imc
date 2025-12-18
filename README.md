<<<<<<< HEAD
# Calculadora de IMC e Saúde

Um aplicativo Android moderno desenvolvido em Kotlin que calcula o Índice de Massa Corporal (IMC) e outras métricas importantes de saúde, com armazenamento de histórico de cálculos.

## 📱 Sobre o Projeto

A **Calculadora de IMC e Saúde** é um aplicativo completo que permite aos usuários calcular diversas métricas de saúde, incluindo IMC, Taxa Metabólica Basal (TMB), peso ideal, necessidade calórica diária e ingestão recomendada de água. O aplicativo salva automaticamente todos os cálculos realizados, permitindo que o usuário acompanhe sua evolução ao longo do tempo.

## ✨ Funcionalidades

### Cálculos Disponíveis

- **IMC (Índice de Massa Corporal)**: Calcula e classifica o IMC de acordo com os padrões da OMS
- **TMB (Taxa Metabólica Basal)**: Calcula o gasto calórico em repouso usando a fórmula de Mifflin-St Jeor
- **Peso Ideal**: Calcula o peso ideal baseado na altura e sexo
- **Necessidade Calórica Diária**: Calcula as calorias necessárias para atingir/manter o peso ideal, considerando o nível de atividade física
- **Ingestão de Água**: Calcula a quantidade recomendada de água diária baseada no peso

### Recursos do App

- ✅ Interface moderna e intuitiva com Material Design 3
- ✅ Histórico completo de cálculos salvos automaticamente
- ✅ Navegação entre telas usando Navigation Compose
- ✅ Persistência de dados com Room Database
- ✅ Suporte a diferentes níveis de atividade física:
  - Sedentário
  - Leve (1-3 dias/semana)
  - Moderado (3-5 dias/semana)
  - Intenso (6-7 dias/semana)
- ✅ Classificação visual do IMC com cores diferentes
- ✅ Validação de campos de entrada

## 🛠️ Tecnologias Utilizadas

### Linguagem e Framework
- **Kotlin** - Linguagem de programação
- **Android SDK** - Plataforma de desenvolvimento
- **Jetpack Compose** - Framework de UI declarativa

### Bibliotecas Principais
- **Room Database** (v2.6.1) - Persistência de dados local
- **Navigation Compose** (v2.7.7) - Navegação entre telas
- **Material 3** - Design system moderno
- **Lifecycle Components** - Gerenciamento de ciclo de vida
- **ViewModel** - Arquitetura MVVM

### Versões
- **MinSdk**: 24 (Android 7.0)
- **TargetSdk**: 36
- **CompileSdk**: 36
- **Kotlin**: 2.0.21
- **AGP**: 8.13.2

## 📂 Estrutura do Projeto

```
app/src/main/java/com/keila/calculadoradeimc/
├── data/
│   ├── AppDatabase.kt          # Configuração do banco de dados Room
│   ├── Historico.kt            # Entidade de dados
│   └── HistoricoDao.kt         # Interface de acesso aos dados
├── datasource/
│   └── Calculations.kt         # Lógica de cálculos de saúde
├── view/
│   ├── Home.kt                 # Tela principal com formulário
│   ├── TelaHistorico.kt        # Tela de histórico de cálculos
│   ├── CalculadoraViewModel.kt # ViewModel para gerenciar estado
│   └── CalculadoraViewModelFactory.kt # Factory para ViewModel
├── ui/theme/
│   ├── Color.kt                # Cores do tema
│   ├── Theme.kt                # Tema do aplicativo
│   └── Type.kt                 # Tipografia
└── MainActivity.kt              # Activity principal
```

## 🚀 Como Executar

### Pré-requisitos

- Android Studio Hedgehog (2023.1.1) ou superior
- JDK 11 ou superior
- Android SDK com API 24 ou superior
- Dispositivo Android ou Emulador

### Instalação

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/CalculadoradeIMC.git
```

2. Abra o projeto no Android Studio

3. Sincronize o Gradle (o Android Studio fará isso automaticamente)

4. Execute o aplicativo:
   - Conecte um dispositivo Android ou inicie um emulador
   - Clique em "Run" ou pressione `Shift + F10`

### Build do APK

Para gerar um APK de debug:
```bash
./gradlew assembleDebug
```

O APK será gerado em: `app/build/outputs/apk/debug/app-debug.apk`

## 📖 Como Usar

1. **Tela Principal (Home)**:
   - Preencha os campos: Peso (kg), Altura (cm) e Idade
   - Selecione o sexo (Masculino ou Feminino)
   - Escolha o nível de atividade física
   - Clique em "CALCULAR E SALVAR"

2. **Visualizar Resultados**:
   - Os resultados aparecem logo abaixo do botão
   - O IMC é exibido com classificação e cor correspondente:
     - 🔵 Azul: Abaixo do peso
     - 🟢 Verde: Peso normal
     - 🟡 Amarelo: Sobrepeso
     - 🔴 Vermelho: Obesidade

3. **Histórico**:
   - Clique em "VER HISTÓRICO" para ver todos os cálculos salvos
   - Cada registro mostra:
     - Data e hora do cálculo
     - IMC e classificação
     - Peso e altura informados
     - Peso ideal
     - Metabolismo basal (TMB)
     - Necessidade calórica diária
     - Ingestão recomendada de água

## 🧮 Fórmulas Utilizadas

### IMC
```
IMC = Peso (kg) / (Altura (m))²
```

### Classificação IMC
- Abaixo do peso: < 18.5
- Peso normal: 18.5 - 24.9
- Sobrepeso: 25.0 - 29.9
- Obesidade Grau I: 30.0 - 34.9
- Obesidade Grau II: 35.0 - 39.9
- Obesidade Grau III: ≥ 40.0

### TMB (Fórmula de Mifflin-St Jeor)
```
Homens: TMB = (10 × peso) + (6.25 × altura) - (5 × idade) + 5
Mulheres: TMB = (10 × peso) + (6.25 × altura) - (5 × idade) - 161
```

### Peso Ideal
```
Altura em polegadas = altura (cm) / 2.54
Excesso = altura_polegadas - 60 (se > 60)

Homens: Peso Ideal = 50 + (2.3 × excesso)
Mulheres: Peso Ideal = 45.5 + (2.3 × excesso)
```

### Necessidade Calórica
```
TMB no Peso Ideal × Fator de Atividade
```

Fatores de Atividade:
- Sedentário: 1.2
- Leve: 1.375
- Moderado: 1.55
- Intenso: 1.725

### Ingestão de Água
```
Água (L) = (Peso (kg) × 35) / 1000
```

## 🏗️ Arquitetura

O projeto segue a arquitetura **MVVM (Model-View-ViewModel)**:

- **Model**: Entidades Room (`Historico`) e lógica de negócio (`Calculations`)
- **View**: Telas Compose (`Home`, `TelaHistorico`)
- **ViewModel**: `CalculadoraViewModel` que gerencia o estado e a lógica de apresentação

### Fluxo de Dados

1. Usuário preenche os dados na `Home`
2. Ao clicar em "CALCULAR E SALVAR", os dados são processados
3. `CalculadoraViewModel` executa os cálculos e salva no banco
4. O banco Room atualiza automaticamente a lista observável
5. A UI reage às mudanças e exibe os resultados

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 👤 Autor

**Keila**

## 🤝 Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para abrir uma issue ou enviar um pull request.

## ⚠️ Aviso Legal

Este aplicativo é apenas para fins informativos e educacionais. Os cálculos fornecidos são estimativas e não substituem a orientação de um profissional de saúde qualificado. Sempre consulte um médico ou nutricionista para avaliações precisas de saúde.

## 📞 Suporte

Se você encontrar algum problema ou tiver sugestões, por favor, abra uma issue no repositório.

---

**Desenvolvido com ❤️ usando Kotlin e Jetpack Compose**

=======
# calculadora-imc
>>>>>>> 234b175432dfb6f39e652115cad31b98dd5007ae
