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
  
task 'deploy-lexicon' => 'pipeline:pelican:lexicon:deploy'
task 'render-lexicon' => 'pipeline:pelican:lexicon:render'

define 'pipeline' do
  project.version = '2.0.1'
  
  define 'api' do
    eclipse.natures :java
    package :jar
  end
  
  define 'util' do
    compile.with LOMBOK, GUAVA,
      projects('pipeline:api')
    package :jar
  end
  
  define 'pelican' do
    eclipse.natures :java
    
    compile.options.source = '1.5'
    compile.options.target = '1.5'
    
    # project components
    define 'api' do
      compile.with LOMBOK,GUAVA,
        projects('pipeline:api','pipeline:util'),
        projects('lexicon','lambdacalc','predcalc','settings')
      test.with LOMBOK,JPARSEC,GUAVA,SNAKE_YAML,SLF4J
      package :jar
    end
    define 'lexicon' do
      compile.with LOMBOK, GUAVA,
        projects('pipeline:api','lambdacalc','settings')
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
        convert = project('lex2tex')._(:target,exe('LeX2TeX'))
        texfile = _(:target,'lexicon.tex')
        puts render = "#{convert} < #{lexicon} > #{texfile}"
        fail unless system render
        puts pandoc = "pandoc -H #{headers} -o #{pdffile} #{texfile}"
        fail unless system pandoc
      end
      
      task :render => pdffile
    end
    define 'lex2tex' do
      source = _(:src,:main,:hs,'LeX2TeX.hs')
      target = _(:target,exe('LeX2TeX'))
      
      file target => source do
        mkdir_p _(:target)
        puts make = "ghc --make -o #{target} #{source}"
        fail unless system make
      end
      compile.with target
    end
    define 'lambdacalc' do
      compile.with LOMBOK, GUAVA, JPARSEC
      package :jar
    end
    define 'predcalc' do
      compile.with LOMBOK, SLF4J,
        projects('lambdacalc','settings')
      package :jar
    end
    define 'settings' do
      compile.with LOMBOK, SNAKE_YAML
      package :jar
    end
  end
end