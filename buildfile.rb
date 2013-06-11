# maven repositories
repositories.remote << 'http://repo1.maven.org/maven2'

# maven dependencies
LOMBOK      = 'org.projectlombok:lombok:jar:0.11.6'
JUNIT4      = 'junit:junit:jar:4.10'
JPARSEC     = 'jparsec:jparsec:jar:2.0.1'
GUAVA       = 'com.google.guava:guava:jar:13.0.1'
SNAKE_YAML  = 'org.yaml:snakeyaml:jar:1.8'
SLF4J       = struct(
  :api     => 'org.slf4j:slf4j-api:jar:1.7.3',
  :simple  => 'org.slf4j:slf4j-simple:jar:1.7.3')
PIPELINE    = struct(
  :api     => 'pipeline:pipeline-api:jar:2.0.2',
  :util    => 'pipeline:pipeline-util:jar:2.0.2')

task 'deploy-lexicon' => 'pelican:lexicon:deploy'
task 'render-lexicon' => 'pelican:lexicon:render'

define 'pelican' do
  project.version = '1.1.0'

  eclipse.natures :java
  compile.options.source = '1.5'
  compile.options.target = '1.5'

  # implementation of pipeline-api
  define 'api' do
    compile.with LOMBOK,GUAVA,PIPELINE,
      projects('lexicon','lambdacalc','predcalc','settings')
    test.with LOMBOK,JPARSEC,GUAVA,SNAKE_YAML,SLF4J,PIPELINE
    package :jar
  end

  # implementation of lexicon file rendering
  define 'lex2tex' do
  end

  # implementation of lexicon file parsing
  define 'lexicon' do
    compile.with LOMBOK,GUAVA,PIPELINE.api,
      projects('lambdacalc','settings')
    package :jar

    task :deploy do
      path = SemAnTE['Lexicon','Default']
      host = SemAnTE['Server','Hostname']
      user = SemAnTE['Server','Username']
      puts pscp = "pscp #{path} #{user}@#{host}:/#{user}/.semante/#{File.basename(path)}"
      fail unless system pscp
    end

    pdffile = _(:target,'lexicon.pdf')
    lexicon = _(:src,:main,:resources,'default.lex')
    headers = _(:src,:main,:resources,'default.preamble')

    file pdffile => ['lex2tex:compile',lexicon,headers] do
      mkdir_p _(:target)
      convert = project('lex2tex')._(:target,'lex2tex')
      texfile = _(:target,'lexicon.tex')
      puts render = "#{convert} < #{lexicon} > #{texfile}"
      fail unless system render
      puts pandoc = "pandoc -N -H #{headers} -o #{pdffile} #{texfile}"
      fail unless system pandoc
    end

    task :render => pdffile
  end

  # implementation of simply typed lambda calculus
  define 'lambdacalc' do
    compile.with LOMBOK,GUAVA,JPARSEC
    package :jar
  end

  # implementation of first order logic and smashing from hol to fol
  define 'predcalc' do
    compile.with LOMBOK,SLF4J,projects('lambdacalc','settings')
    package :jar
  end

  # implementation of configuration files
  define 'settings' do
    compile.with LOMBOK,SNAKE_YAML
    package :jar
  end
end
