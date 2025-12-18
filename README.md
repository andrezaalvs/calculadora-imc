# Calculadora de Saúde e Bem-Estar

> **Documentação Técnica**  
> Aplicativo Android desenvolvido em Kotlin para análise multidimensional da condição física do usuário

---

## 📋 Informações Acadêmicas

**Universidade:** Universidade Federal de Uberlândia (UFU)  
**Faculdade:** Faculdade de Ciências da Computação  
**Curso:** Sistemas de Informação  
**Disciplina:** Programação para Dispositivos Móveis  
**Professor:** Dr. Alexsandro Santos Soares  
**Ano:** 2025

**Autores:**
- **Andreza Batista Alves** (12311BSI246)
- **Keila Almeida Santana** (12321BSI213)

---

## 📱 Sobre o Projeto

A **Calculadora de Saúde e Bem-Estar** é um aplicativo Android nativo desenvolvido em Kotlin utilizando o framework Jetpack Compose. O aplicativo oferece uma análise multidimensional da condição física do usuário, indo além do cálculo do Índice de Massa Corporal (IMC), por meio da estimativa da ingestão diária de água, da taxa metabólica basal e da necessidade calórica para alcance do peso ideal.

### Objetivo

O objetivo principal da aplicação é fornecer ao usuário uma visão mais ampla da sua condição física, não se limitando ao cálculo do IMC, mas incluindo metas de hidratação, taxa metabólica basal e necessidades calóricas para atingir o peso ideal.

---

## ✨ Funcionalidades

### Cálculos Disponíveis

- **IMC (Índice de Massa Corporal)**: Calcula e classifica o IMC de acordo com os padrões da OMS, fornecendo um diagnóstico nutricional básico
- **TMB (Taxa Metabólica Basal)**: Calcula o gasto energético do corpo em repouso absoluto usando a fórmula de Mifflin-St Jeor
- **Peso Ideal**: Estima o peso ideal baseado na altura e sexo utilizando a fórmula de Devine
- **Necessidade Calórica Diária**: Calcula as calorias necessárias para atingir/manter o peso ideal, considerando o nível de atividade física
- **Ingestão de Água**: Calcula a quantidade recomendada de água diária baseada no peso

### Recursos do App

- ✅ Interface moderna e intuitiva com Material Design 3
- ✅ Histórico completo de cálculos salvos automaticamente
- ✅ Navegação entre telas usando Navigation Compose
- ✅ Persistência de dados com Room Database
- ✅ Atualizações em tempo real usando Flow
- ✅ Suporte a diferentes níveis de atividade física:
  - Sedentário (fator 1.2)
  - Leve (1-3 dias/semana, fator 1.375)
  - Moderado (3-5 dias/semana, fator 1.55)
  - Intenso (6-7 dias/semana, fator 1.725)
- ✅ Classificação visual do IMC com cores diferentes
- ✅ Validação de campos de entrada

---

## 🛠️ Tecnologias Utilizadas

### Linguagem e Framework
- **Kotlin** (v2.0.21) - Linguagem de programação
- **Android SDK** - Plataforma de desenvolvimento
- **Jetpack Compose** - Framework de UI declarativa

### Bibliotecas Principais
- **Room Database** (v2.6.1) - Persistência de dados local (abstração do SQLite)
- **Navigation Compose** (v2.7.7) - Navegação entre telas
- **Material 3** - Design system moderno
- **Lifecycle Components** - Gerenciamento de ciclo de vida
- **ViewModel** - Arquitetura MVVM
- **Coroutines** - Programação assíncrona
- **Flow** - Streams reativos para atualizações em tempo real

### Versões
- **MinSdk**: 24 (Android 7.0)
- **TargetSdk**: 36
- **CompileSdk**: 36
- **Kotlin**: 2.0.21
- **AGP**: 8.13.2

---

## 📂 Estrutura do Projeto

```
app/src/main/java/com/keila/calculadoradeimc/
├── data/
│   ├── AppDatabase.kt          # Configuração do banco de dados Room
│   ├── Historico.kt            # Entidade de dados (Room Entity)
│   └── HistoricoDao.kt         # Interface de acesso aos dados (DAO)
├── datasource/
│   └── Calculations.kt          # Motor de cálculos (lógica de negócio)
├── view/
│   ├── Home.kt                 # Tela principal com formulário (View)
│   ├── TelaHistorico.kt        # Tela de histórico de cálculos (View)
│   ├── CalculadoraViewModel.kt # ViewModel para gerenciar estado
│   └── CalculadoraViewModelFactory.kt # Factory para ViewModel
├── ui/theme/
│   ├── Color.kt                # Cores do tema
│   ├── Theme.kt                # Tema do aplicativo
│   └── Type.kt                 # Tipografia
└── MainActivity.kt              # Activity principal
```

---

## 🧮 Fórmulas Matemáticas e Indicadores

Para garantir a precisão dos resultados apresentados, o motor de cálculo da aplicação, implementado no arquivo `Calculations.kt`, utiliza equações científicas amplamente aceitas na literatura da área da saúde.

### 2.1 Índice de Massa Corporal (IMC)

A fórmula utilizada para o cálculo do IMC é dada por:

```
IMC = peso (kg) / altura (m)²
```

**Classificação IMC:**
- Abaixo do peso: < 18.5
- Peso normal: 18.5 - 24.9
- Sobrepeso: 25.0 - 29.9
- Obesidade Grau I: 30.0 - 34.9
- Obesidade Grau II: 35.0 - 39.9
- Obesidade Grau III: ≥ 40.0

A finalidade desse indicador é fornecer um diagnóstico nutricional básico do usuário, classificando-o em faixas como baixo peso, peso normal, sobrepeso ou obesidade.

### 2.2 Taxa Metabólica Basal (TMB)

Para o cálculo da TMB, foi adotada a equação de **Mifflin-St Jeor**:

**Masculino:**
```
TMB = (10 × peso) + (6,25 × altura) − (5 × idade) + 5
```

**Feminino:**
```
TMB = (10 × peso) + (6,25 × altura) − (5 × idade) − 161
```

Esse indicador representa o gasto energético do corpo em repouso absoluto, considerando o peso atual do usuário.

### 2.3 Peso Ideal

O peso ideal é estimado por meio da **fórmula de Devine**:

**Masculino:**
```
Altura em polegadas = altura (cm) / 2.54
Excesso = altura_polegadas - 60 (se > 60)
Peso Ideal = 50 + (2.3 × excesso)
```

**Feminino:**
```
Altura em polegadas = altura (cm) / 2.54
Excesso = altura_polegadas - 60 (se > 60)
Peso Ideal = 45.5 + (2.3 × excesso)
```

Esse cálculo fornece um valor de referência saudável, utilizado como base para outras estimativas do sistema.

### 2.4 Necessidade Calórica Diária

A necessidade calórica diária é calculada aplicando-se o fator de atividade física sobre a TMB projetada para o peso ideal:

```
Necessidade Calórica = TMB (no Peso Ideal) × Fator de Atividade
```

**Fatores de Atividade:**
- Sedentário: 1.2
- Leve (1-3 dias/semana): 1.375
- Moderado (3-5 dias/semana): 1.55
- Intenso (6-7 dias/semana): 1.725

Esse valor representa a quantidade de calorias que o usuário deve consumir para atingir sua meta de saúde.

### 2.5 Ingestão Diária de Água

A ingestão de água recomendada é calculada pela fórmula:

```
Água (L) = (35 ml × peso) / 1000
```

O resultado é convertido para litros, fornecendo ao usuário uma meta diária de hidratação.

---

## 💾 Modelo de Dados e Persistência

A persistência de dados foi implementada por meio da biblioteca **Room**, que abstrai o banco de dados SQLite, garantindo maior segurança, organização e desempenho.

### 3.1 Modelo de Dados

A entidade principal, definida no arquivo `Historico.kt`, armazena o histórico completo de cada cálculo realizado pelo usuário, contendo os seguintes atributos:

- **id**: Chave primária autoincrementada
- **peso, altura, idade, sexo**: Dados de entrada do usuário
- **imc e classificacaoImc**: Resultados do diagnóstico nutricional
- **tmb**: Taxa metabólica basal calculada
- **pesoIdeal**: Peso ideal estimado
- **necessidadeCalorica**: Necessidade calórica diária
- **ingestaoAgua**: Meta de ingestão de água em litros
- **dataCadastro**: Registro temporal em formato timestamp para ordenação cronológica

### 3.2 Camada de Acesso aos Dados

O acesso aos dados é realizado por meio do `HistoricoDao`, que utiliza **Flow** para fornecer atualizações em tempo real à interface gráfica sempre que novos registros são inseridos, eliminando a necessidade de atualizações manuais da tela.

---

## 🏗️ Arquitetura do Sistema

O projeto segue o padrão arquitetural **MVVM (Model–View–ViewModel)**, promovendo separação de responsabilidades e maior facilidade de manutenção.

### Componentes da Arquitetura

- **View**: Responsável pela renderização da interface e captura das interações do usuário, representada pelas telas `Home` e `TelaHistorico` (implementadas com Jetpack Compose)

- **ViewModel**: Representado pela classe `CalculadoraViewModel`, responsável por gerenciar a lógica de negócio, coordenar os cálculos e intermediar o acesso ao banco de dados por meio de Coroutines

- **Model/Domain**: Camada responsável pelas regras matemáticas do negócio, centralizadas na classe `Calculations`

### Fluxo de Dados

1. Usuário preenche os dados na tela `Home`
2. Ao clicar em "CALCULAR E SALVAR", os dados são processados
3. `CalculadoraViewModel` executa os cálculos através da classe `Calculations`
4. Os resultados são salvos no banco de dados Room através do `HistoricoDao`
5. O banco Room atualiza automaticamente a lista observável (Flow)
6. A UI reage às mudanças e exibe os resultados em tempo real

---

## 🚀 Como Executar

### Pré-requisitos

- Android Studio Hedgehog (2023.1.1) ou superior
- JDK 11 ou superior
- Android SDK com API 24 ou superior
- Dispositivo Android ou Emulador

### Instalação

1. Clone o repositório:
```bash
git clone https://github.com/andrezaalvs/calculadora-imc.git
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

---

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

---

## 🔮 Melhorias Futuras e Escalabilidade

O aplicativo foi desenvolvido de forma modular, permitindo sua evolução em versões futuras. Entre as melhorias previstas, destacam-se:

1. **Implementação de gráficos de progressão**: Utilizando bibliotecas de visualização de dados, para demonstrar a evolução do peso ao longo do tempo

2. **Integração com serviços de geolocalização**: Por meio da Google Maps API, para sugerir academias, parques e centros de lazer próximos ao usuário

3. **Autenticação de usuários**: Com Firebase Authentication, possibilitando o armazenamento dos dados em nuvem e o acesso ao histórico em diferentes dispositivos

4. **Integração com APIs de saúde**: Como Google Fit ou Samsung Health, para sincronização automática de passos e atividades físicas

5. **Exportação de dados**: Permitir exportar o histórico em formatos CSV ou PDF

6. **Lembretes personalizados**: Notificações para ingestão de água e acompanhamento de metas

---

## ⚠️ Aviso Legal

Este aplicativo é apenas para fins informativos e educacionais. Os cálculos fornecidos são estimativas e não substituem a orientação de um profissional de saúde qualificado. Sempre consulte um médico ou nutricionista para avaliações precisas de saúde.

---

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

---

## 🤝 Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para abrir uma issue ou enviar um pull request.

---

## 📞 Suporte

Se você encontrar algum problema ou tiver sugestões, por favor, abra uma issue no repositório: [https://github.com/andrezaalvs/calculadora-imc/issues](https://github.com/andrezaalvs/calculadora-imc/issues)

---

## 📚 Referências

- **Fórmula de Mifflin-St Jeor**: Mifflin, M. D., et al. (1990). A new predictive equation for resting energy expenditure in healthy individuals.
- **Fórmula de Devine**: Devine, B. J. (1974). Gentamicin therapy.
- **Classificação IMC**: Organização Mundial da Saúde (OMS)

---

## 🎓 Considerações Finais

A Calculadora de Saúde e Bem-Estar apresenta-se como uma aplicação que vai além de cálculos simples, oferecendo ao usuário informações relevantes para o acompanhamento da saúde. A utilização de fórmulas consagradas, aliada a uma arquitetura bem definida e à possibilidade de expansão futura, demonstra o potencial da aplicação como uma ferramenta de apoio ao bem-estar e à qualidade de vida.

---

**Desenvolvido com ❤️ usando Kotlin e Jetpack Compose**

*Projeto acadêmico desenvolvido para a disciplina de Programação para Dispositivos Móveis - UFU 2025*
