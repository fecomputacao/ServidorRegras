ServidorRegras
==============

Servidor de regras usando o framework play e a ferramenta drools para geração de regras.

==============

Requerimentos:
- Play!Framework 1.2.5

Instalação:
- Entrar pelo terminal na pasta do projeto
- Executar: 'play deps' para baixar as dependências
- Executar: 'play eclipsify' para configurar o projeto para o eclipse.
- Executar: 'play test' para executar
- URL de acesso: http://localhost:9000



IMPORTANTE
==============

The problem comes as the Drools is not able to resolve the play models.In eclipse it works fine as it add eclipse/classes to the classpath.But whenever you run "Play run" drools is not able to find the play.models.So to overcome this we need to add the "tmp/classes" to the classpath.

Existe um problema no qual o Drools não consegue enxergar as models do play. Para resolver isso, é necessário adicionar a pasta "tmp/classes" ao classpath seguindo os seguintes passos: (vide: http://stackoverflow.com/questions/8398517/only-a-type-can-be-imported-models-xyz-resolves-to-a-package)


1. Ir para o diretório de instalação do play e abrir as pastas >> framework >> pym >> play

2. Abrir o arquivo application.py

3. Encontre o método 'def getClasspath(self):'

4. Dentro do método encontre a seguinte linha 'classpath.append(os.path.normpath(os.path.join(self.path, 'conf')))' e insira abaixo a seguinte linha:

	classpath.append(os.path.normpath(os.path.join(os.path.join(self.path, 'tmp'),'classes')))

5. Agora o drools irá encontrar as models do play.