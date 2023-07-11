<h1>API de Processo Seletivo</h1>

<p>Bem-vindo à documentação da API de Processo Seletivo. Esta API fornece endpoints para gerenciar o processo seletivo de candidatos.</p>

<h2>Endpoints</h2>

<h3>Iniciar Processo Seletivo</h3>
<p>Endpoint para iniciar o processo seletivo para um candidato.</p>

POST /api/v1/hiring/start

<p>Parâmetros de entrada:</p>
<ul>
    <li><code>nome</code> (string): O nome do candidato.</li>
</ul>

<p>Resposta de sucesso:</p>
<ul>
    <li><code>codCandidato</code> (inteiro): O código do candidato iniciado.</li>
</ul>

<h3>Marcar Entrevista</h3>
<p>Endpoint para marcar a entrevista de um candidato.</p>

POST /api/v1/hiring/schedule

<p>Parâmetros de entrada:</p>
<ul>
    <li><code>codCandidato</code> (inteiro): O código do candidato.</li>
</ul>

<p>Resposta de sucesso:</p>
<ul>
    <li>A mensagem "Entrevista marcada para o candidato."</li>
</ul>

<h3>Desqualificar Candidato</h3>
<p>Endpoint para desqualificar um candidato.</p>

POST /api/v1/hiring/disqualify

<p>Parâmetros de entrada:</p>
<ul>
    <li><code>codCandidato</code> (inteiro): O código do candidato.</li>
</ul>

<p>Resposta de sucesso:</p>
<ul>
    <li>A mensagem "Candidato desqualificado com sucesso."</li>
</ul>

<h3>Aprovar Candidato</h3>
<p>Endpoint para aprovar um candidato.</p>

POST /api/v1/hiring/approve

<p>Parâmetros de entrada:</p>
<ul>
    <li><code>codCandidato</code> (inteiro): O código do candidato.</li>
</ul>

<p>Resposta de sucesso:</p>
<ul>
    <li>A mensagem "Candidato aprovado com sucesso."</li>
</ul>

<h3>Verificar Status do Candidato</h3>
<p>Endpoint para verificar o status de um candidato.</p>

GET /api/v1/hiring/status/candidate/{id}

<p>Parâmetros de caminho:</p>
<ul>
    <li><code>id</code> (inteiro): O código do candidato.</li>
</ul>

<p>Resposta de sucesso:</p>
<ul>
    <li>O status do candidato.</li>
</ul>

<h3>Obter Candidatos Aprovados</h3>
<p>Endpoint para obter a lista de candidatos aprovados.</p>

GET /api/v1/hiring/approved

<p>Resposta de sucesso:</p>
<ul>
    <li>Uma lista contendo os nomes dos candidatos aprovados.</li>
</ul>
