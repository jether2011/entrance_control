# Controle de Reuniões - CCB

A atual estrutura da CCB necessita, para manutenção de toda organização, de reuniões semanais em praticamente quase a sua totalidade nacional. 

A estrutura citada atualmente está dividida em núcleos regionais os quais também estão divididos em administrações e estas, podem, na maioria das vezes, possuir agregação de cidades compondo assim administrações semi-regionais. Ex.: Adm. Guaratinguetá - Agregadas: Lorena, Piquete, Cunha, Potim e Aparecida.

Nesse caso, reuniões recorrentes são necessárias assim como a necessidade de organização e agendamento dos salões de reuniões, autenticação de colaboradores e integrantes do corpo ministerial e relatórios de presença. Visto essa necessidade, nasce a iniciativa de ser desenvolvido um sistema que possua essas funcionalidades e seja passível de fácil manutenção em futuros problemas ou evolução que possa ser requirido para o mesmo. 

O sistema que será desenvolvido irá, em primeiro momento, ser testado, em modelo piloto, por uma das administrações e após homologação, poderá extender para as outras administrações da administração regional do Vale do Paraíba (RAVAP) e até mesmo ganhar proporções nacional ou mundial.

### Necessidades

Tendo em mente essa futura evolução, o sistema de agendamento, controle e emissão de relatório de reuniões será desenvolvido pensando nos seguintes pontos:

1. Escalabilidade - Necessidade de escalar facilmente caso necessário;

2. Disponibilidade - Necessidade de estar disponível em pelo menos 99% do tempo;

3. Integridade - Necessidade dos dados serem íntegros na transmissão e recebimento dos mesmos;

4. Confiabilidade - Necessidade de apenas pessoas autorizadas e autenticadas possuem a possibilidade de manipular as informações e funcionalidades;

5. Observalidade - Monitoramento das aplicações e serviços.

Visto tais pontos, conseguimos atendê-los ao utilizar-se das ferramentas:

1. Utilizaremos arquitetura de microsserviços (Facilidade em manter pois os sistemas são menores, menos lógica, utilzar arquitetura de containers - docker - para realizar as implantações de cada serviço e possibilidade de escalonamento individual dos serviços);

2. Com Docker conseguimos implementar balanceamento, gateway e outras facilidades;

3. Necessidade de existir certificado SSL para atender a camada de segurança do protocolo HTTP, o security layer (HTTPS);

4. Microsserviço não utiliza session, pois por princípio é Stateless (sem estado), logo, para conseguirmos confiabilidade utilizaremos uma ferramenta que extende ao OAuth, a JWT;

5. Utilizaremos de ferramentas presente no Spring Cloud que nos permitirá implementar funcionalidades que auxiliarão na monitoração dos serviços.

### Tecnologias

Pilha de tecnologias que deverão ser utilizadas:
1. SpŕingBoot + Spring Cloud;

2. Banco de dados relacional para os serviços (cada serviço deverá manter o seu próprio banco de dados). Para o processo de relatório, deverá ser criada views que naveguem entre os banco de dados e expõe as informações necessárias para os relatórios. Cada microsserviço, tendo seu próprio banco de dados, irá armazenar sempre apenas as chaves externas para referencias dos domínios do serviços consumido. O banco de dados será o PostgreSQL e será utilizado o Spring Data para abstração da lógica de negócio e implementações de repositórios;

3. Ainda, pensando em auxiliar a manutenção, será utilizado, para gestão de migraçõess de banco de dados (novas alterações/criações) um micro framework, de fácílima integração com o SpringBoot, FlyWay DB. A nomenclatura das migrations deverão seguir a proposta: VyyyyMMddHHMMSS__nome_da_acao (Ex.: V20190209163000__Create_Tables.sql)

4. Spotify plugin para geração de jars dos serviços;

5. Registry interno para publicação das imagens (ou criação de um repositório privado na Docker Hub);

6. Docker para implantação dos serviços;

7. Proxmox para virtualização do Hardware (servidor).


#### Diagrama inicial de entidades


![](https://github.com/jether2011/entrance_control/blob/master/controle_portaria.png?raw=true)


#### Diagrama caso de uso - Controle de Reuniões - CCB

![](https://github.com/jether2011/entrance_control/blob/master/diagrama_CDU.png?raw=true)

